import { useEffect, useState } from "react";
import { Button, Container } from "react-bootstrap";
import Product from "./Product";
import { config } from "../../config";

function ProductsList() {
  const [allProducts, setAllProducts] = useState([]);
  const [products, setProducts] = useState([]);
  const [activeCategory, setActiveCategory] = useState("all");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    getAll();
  }, []);

  async function getAll() {
    setLoading(true);
    setError(null);

    try {
      const token = localStorage.getItem("accessToken");
      const response = await fetch(`${config.apiUrl}/v1/products`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const result = await response.json();
      const fetchedProducts = result?.data || [];

      setAllProducts(fetchedProducts);
      setProducts(fetchedProducts);
      setActiveCategory("all");
    } catch (err) {
      console.error("Fetch error:", err);
      setError("Failed to load products.");
    } finally {
      setLoading(false);
    }
  }

  function filterByCategory(categoryName) {
    setActiveCategory(categoryName);

    if (categoryName === "all") {
      setProducts(allProducts);
    } else {
      const filtered = allProducts.filter(
        (product) =>
          product.categoryName &&
          product.categoryName.toLowerCase() === categoryName.toLowerCase()
      );
      setProducts(filtered);
    }
  }

  const buttonStyle = (category) => ({
    background:
      activeCategory === category
        ? "linear-gradient(135deg, #2c3e50 0%, #4ca1af 100%)"
        : "transparent",
    color: activeCategory === category ? "white" : "#2c3e50",
    border: "1px solid #2c3e50",
    padding: "8px 16px",
    fontWeight: "500",
    transition: "all 0.3s ease",
  });

  return (
    <Container>
      <h1 className="text-center mt-3">Latest Products</h1>
      <hr />
      <div className="d-flex justify-content-center gap-3 my-4 filter-btn flex-wrap">
        <Button
          style={buttonStyle("all")}
          onClick={() => filterByCategory("all")}
          className="category-btn"
        >
          All
        </Button>
        <Button
          style={buttonStyle("Men's Clothing")}
          onClick={() => filterByCategory("Men's Clothing")}
          className="category-btn"
        >
          Men's Clothing
        </Button>
        <Button
          style={buttonStyle("Women's Clothing")}
          onClick={() => filterByCategory("Women's Clothing")}
          className="category-btn"
        >
          Women's Clothing
        </Button>
        <Button
          style={buttonStyle("Accessories")}
          onClick={() => filterByCategory("Accessories")}
          className="category-btn"
        >
          Accessories
        </Button>
        <Button
          style={buttonStyle("Footwear")}
          onClick={() => filterByCategory("Footwear")}
          className="category-btn"
        >
          Footwear
        </Button>
        <Button
          style={buttonStyle("Outerwear")}
          onClick={() => filterByCategory("Outerwear")}
          className="category-btn"
        >
          Outerwear
        </Button>
      </div>

      {loading && <p className="text-center">Loading products...</p>}
      {error && <p className="text-danger text-center">{error}</p>}
      {!loading && !error && <Product products={products} />}
    </Container>
  );
}

export default ProductsList;
