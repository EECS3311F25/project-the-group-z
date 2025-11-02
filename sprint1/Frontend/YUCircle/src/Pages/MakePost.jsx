import React, { useState } from "react";

function MakePost() {
  const [username, setUsername] = useState("");
  const [content, setContent] = useState("");
  const [imageUrl, setImageUrl] = useState("");
  const [response, setResponse] = useState("");
  

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch("http://localhost:8080/api/posts/create", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, content, imageUrl }),
      });

      if (res.ok) {
        const text = await res.text();
        setResponse(text);
      } else {
        setResponse("Error: Could not create post");
      }
    } catch (err) {
      setResponse("Server error: " + err.message);
    }
  };

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Create a Post</h1>
      <form onSubmit={handleSubmit} className="flex flex-col gap-4 max-w-md">
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="border p-2 rounded"
        />
        <textarea
          placeholder="Write your post..."
          value={content}
          onChange={(e) => setContent(e.target.value)}
          className="border p-2 rounded"
        />
        <input
          type="text"
          placeholder="Image URL (optional)"
          value={imageUrl}
          onChange={(e) => setImageUrl(e.target.value)}
          className="border p-2 rounded"
        />
        <button
          type="submit"
          className="bg-red-600 text-white py-2 px-4 rounded hover:bg-red-700"
        >
          Post
        </button>
      </form>

      {response && <p className="mt-4 text-green-600">{response}</p>}
    </div>
  );
}

export default MakePost;
