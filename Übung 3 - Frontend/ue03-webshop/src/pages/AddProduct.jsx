import { createSignal } from "solid-js";
import { useNavigate } from "@solidjs/router"; // Import the navigation hook

export default function AddProduct() {
  const [title, setTitle] = createSignal("");
  const [description, setDescription] = createSignal("");
  const [price, setPrice] = createSignal("");
  const [img, setImg] = createSignal("");
  const [statusMessage, setStatusMessage] = createSignal("");

  const navigate = useNavigate(); // Initialize the navigate function

  const handleSubmit = async (e) => {
    e.preventDefault();

    const product = {
      title: title(),
      description: description(),
      price: parseInt(price()),
      img: img(),
    };

    try {
      const response = await fetch("http://localhost:8080/api/products", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(product),
      });

      if (response.ok) {
        const savedProduct = await response.json();
        setStatusMessage(`Product "${savedProduct.title}" added successfully!`);
        // Reset form fields
        setTitle("");
        setDescription("");
        setPrice("");
        setImg("");

        // Redirect to the homepage after 1 second
        navigate("/");
      } else {
        setStatusMessage("Failed to add product. Please try again.");
      }
    } catch (error) {
      setStatusMessage("An error occurred while adding the product.");
    }
  };

  return (
    <div class="my-7">
      <h2 class="text-3xl font-bold mb-5">Add a New Product</h2>
      <form onSubmit={handleSubmit} class="space-y-5">
        <div>
          <label for="title" class="block font-semibold mb-2">
            Title
          </label>
          <input
            type="text"
            id="title"
            value={title()}
            onInput={(e) => setTitle(e.target.value)}
            class="input input-bordered w-full"
            placeholder="Enter product title"
            required
          />
        </div>
        <div>
          <label for="description" class="block font-semibold mb-2">
            Description
          </label>
          <textarea
            id="description"
            value={description()}
            onInput={(e) => setDescription(e.target.value)}
            class="textarea textarea-bordered w-full"
            placeholder="Enter product description"
            required
          ></textarea>
        </div>
        <div>
          <label for="price" class="block font-semibold mb-2">
            Price
          </label>
          <input
            type="number"
            id="price"
            value={price()}
            onInput={(e) => setPrice(e.target.value)}
            class="input input-bordered w-full"
            placeholder="Enter product price"
            required
          />
        </div>
        <div>
          <label for="img" class="block font-semibold mb-2">
            Image URL
          </label>
          <input
            type="text"
            id="img"
            value={img()}
            onInput={(e) => setImg(e.target.value)}
            class="input input-bordered w-full"
            placeholder="Enter product image URL"
            required
          />
        </div>
        <button type="submit" class="btn btn-primary w-full">
          Add Product
        </button>
      </form>
      {statusMessage() && (
        <div class="mt-5 p-4 border rounded text-center bg-gray-100">
          {statusMessage()}
        </div>
      )}
    </div>
  );
}
