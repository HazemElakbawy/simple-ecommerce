import React, { useState, useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
// import { faStar } from "@fortawesome/free-solid-svg-icons";
import { Container, Row, Col, Button, Form } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";
import { AddedToCartMsg } from "../UsersMsg/AddedToCartMsg";
import { useCart } from "../../contexts/CartContext";
import { config } from "../../config";

function ProductDetails() {
  const { productId } = useParams();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const { addToCart } = useCart();
  const [selectedColor, setSelectedColor] = useState("");
  const [selectedSize, setSelectedSize] = useState("");

  // Sample colors and sizes - you can modify these based on your needs
  const colors = ["Black", "White", "Red", "Blue", "Green"];
  const sizes = ["S", "M", "L", "XL", "XXL"];

  const accessToken = localStorage.getItem("accessToken"); // Assuming it's in localStorage

  useEffect(() => {
    const fetchProductDetails = async () => {
      setLoading(true);
      setError(null);
      try {
        const response = await fetch(
          `${config.apiUrl}/v1/products/${productId}`,
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          }
        );
        if (!response.ok) {
          const errorData = await response.json();
          throw new Error(
            errorData.message || "Failed to fetch product details"
          );
        }
        const data = await response.json();
        setProduct(data.data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchProductDetails();
  }, [productId, accessToken]);

  const handleAddToCart = () => {
    if (!selectedColor || !selectedSize) {
      alert("Please select both color and size");
      return;
    }
    //  addToCart expects an object with product details, not just the ID.
    //  We need to pass the necessary product information.
    addToCart({
      id: product.id,
      name: product.name,
      price: 0, // You might not have price in your new product data, or it might be in a different field.
      selectedColor: selectedColor,
      selectedSize: selectedSize,
      quantity: 1, // Or get quantity from input if you add that functionality
      pictureUrl: product.pictureUrl,
    });
    AddedToCartMsg();
  };

  if (loading) {
    return (
      <Container className="my-5 text-center">
        <p>Loading product details...</p>
        {/* You can add a more sophisticated loader here, like a spinner */}
      </Container>
    );
  }

  if (error) {
    return (
      <Container className="my-5 text-center">
        <p className="text-danger">Error: {error}</p>
      </Container>
    );
  }

  if (!product) {
    return (
      <Container className="my-5 text-center">
        <p>Product not found.</p>
      </Container>
    );
  }

  return (
    <Container>
      <Row className="my-5 align-items-center text-center">
        <Col lg={6} md={6} sm={12}>
          <div>
            <img
              className="img-fluid product-image"
              src={product.pictureUrl}
              alt={product.name}
              style={{ width: "350px", height: "400px" }}
            />
          </div>
        </Col>
        <Col lg={6} md={6} sm={12}>
          <h6 className="fs-5 text-black-50">{product.categoryName}</h6>
          <h1 className="display-5">{product.name}</h1>
          {/* <p className="fs-4">
            {product.rating !== undefined ? product.rating.rate : null}
            <FontAwesomeIcon className="ms-1" fontSize={22} icon={faStar} />
          </p> */}
          {/* Removed price. */}
          {/* <p className="display-6">${product.price}</p> */}
          <p className="text-black-50">{product.description}</p>

          {/* Color Selection */}
          <Form.Group className="mb-3">
            <Form.Label>Select Color</Form.Label>
            <div className="d-flex gap-2 justify-content-center">
              {colors.map((color) => (
                <Button
                  key={color}
                  variant={selectedColor === color ? "dark" : "outline-dark"}
                  onClick={() => setSelectedColor(color)}
                  style={{ minWidth: "80px" }}
                >
                  {color}
                </Button>
              ))}
            </div>
          </Form.Group>

          {/* Size Selection */}
          <Form.Group className="mb-3">
            <Form.Label>Select Size</Form.Label>
            <div className="d-flex gap-2 justify-content-center">
              {sizes.map((size) => (
                <Button
                  key={size}
                  variant={selectedSize === size ? "dark" : "outline-dark"}
                  onClick={() => setSelectedSize(size)}
                  style={{ minWidth: "50px" }}
                >
                  {size}
                </Button>
              ))}
            </div>
          </Form.Group>

          <Button variant="dark" className="me-3" onClick={handleAddToCart}>
            Add to Cart
          </Button>
          <Link to={"/cart"} className="btn btn-dark">
            Go to Cart
          </Link>
        </Col>
      </Row>
    </Container>
  );
}

export default ProductDetails;
