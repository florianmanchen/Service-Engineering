import { useParams, useNavigate } from "@solidjs/router";
import { createResource, createSignal, Show } from "solid-js";
import { useCartContext } from "../context/CartContext";

const fetchProduct = async (id) => {
  const res = await fetch("http://localhost:8080/api/products/" + id);
  return res.json();
};

export default function Product() {
  const params = useParams();
  const navigate = useNavigate();
  const [product, { refetch }] = createResource(params.id, fetchProduct);

  const { items, setItems } = useCartContext();

  const [adding, setAdding] = createSignal(false);
  const [updating, setUpdating] = createSignal(false);
  const [editing, setEditing] = createSignal(false); // New state for editing
  const [statusMessage, setStatusMessage] = createSignal("");

  const [editableTitle, setEditableTitle] = createSignal(""); // Editable title
  const [editableDescription, setEditableDescription] = createSignal(""); // Editable description

  const addProduct = () => {
    setAdding(true);
    setTimeout(() => setAdding(false), 2000);

    const exists = items.find((p) => p.id === product().id);

    if (exists) {
      setItems((p) => p.id === product().id, "quantity", (q) => q + 1);
    } else {
      setItems([...items, { ...product(), quantity: 1 }]);
    }
  };

  const enableEditing = () => {
    setEditing(true);
    setEditableTitle(product().title); // Load current title into editable field
    setEditableDescription(product().description); // Load current description
  };

  const saveEdits = async () => {
    setUpdating(true);

    const updatedProduct = {
      title: editableTitle(),
      description: editableDescription(),
      price: product().price,
      img: product().img,
    };

    try {
      const response = await fetch(
        `http://localhost:8080/api/products/${params.id}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(updatedProduct),
        }
      );

      if (response.ok) {
        setStatusMessage("Product updated successfully!");
        setEditing(false); // Disable editing mode
        refetch(); // Refresh product details
      } else {
        setStatusMessage("Failed to update product.");
      }
    } catch (error) {
      setStatusMessage("An error occurred while saving edits.");
    } finally {
      setUpdating(false);
    }
  };

  const deleteProduct = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/products/${params.id}`,
        { method: "DELETE" }
      );

      if (response.ok) {
        setStatusMessage("Product deleted successfully!");
        setTimeout(() => navigate("/"), 500);
      } else {
        setStatusMessage("Failed to delete product.");
      }
    } catch (error) {
      setStatusMessage("An error occurred while deleting the product.");
    }
  };

  return (
    <div class="my-7">
      <Show when={product()} fallback={<p>Loading product...</p>}>
        <div class="grid grid-cols-5 gap-7">
          <div class="col-span-2">
            <img src={product().img} alt="product image" />
          </div>

          <div class="col-span-3">
            <div class="mb-7">
              {editing() ? (
                <div>
                  <input
                    type="text"
                    value={editableTitle()}
                    onInput={(e) => setEditableTitle(e.target.value)}
                    class="input input-bordered w-full mb-2"
                  />
                  <textarea
                    value={editableDescription()}
                    onInput={(e) => setEditableDescription(e.target.value)}
                    class="textarea textarea-bordered w-full"
                  ></textarea>
                </div>
              ) : (
                <div>
                  <h2 class="text-3xl font-bold">{product().title}</h2>
                  <p>{product().description}</p>
                </div>
              )}
            </div>
            <p class="my-7 text-2xl">Only £{product().price}</p>

            <button class="btn" onClick={addProduct} disabled={adding()}>
              Add to Cart
            </button>

            <Show when={adding()}>
              <div class="m-2 p-2 border-amber-500 border-2 rounded-md inline-block">
                {product().title} was added to the cart
              </div>
            </Show>

            {editing() ? (
              <div>
                <button
                  class="btn btn-primary mt-4"
                  onClick={saveEdits}
                  disabled={updating()}
                >
                  {updating() ? "Saving..." : "Save Changes"}
                </button>
                <button
                  class="btn btn-secondary mt-4 ml-4"
                  onClick={() => setEditing(false)}
                >
                  Cancel
                </button>
              </div>
            ) : (
              <button class="btn btn-secondary mt-4" onClick={enableEditing}>
                Edit Product
              </button>
            )}

            <button
              class="btn btn-danger mt-4 ml-4"
              onClick={deleteProduct}
              disabled={deleting()}
            >
              {deleting() ? "Deleting..." : "Delete Product"}
            </button>

            <Show when={statusMessage()}>
              <div class="mt-4 p-4 border rounded text-center bg-gray-100">
                {statusMessage()}
              </div>
            </Show>
          </div>
        </div>
      </Show>
    </div>
  );
}
