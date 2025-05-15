import React, { createContext, useContext, useState, useEffect } from "react";
import { config } from "../config";

const CartContext = createContext();

export const useCart = () => {
  return useContext(CartContext);
};

export const CartProvider = ({ children }) => {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const accessToken = localStorage.getItem("accessToken");

  const fetchCart = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await fetch(`${config.apiUrl}/v1/cart`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Failed to fetch cart");
      }
      const data = await response.json();
      setItems(data.data.items);
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    if (token) {
      const fetchData = async () => {
        setLoading(true);
        setError(null);
        try {
          const response = await fetch(`${config.apiUrl}/v1/cart`, {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          });
          if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || "Failed to fetch cart");
          }
          const data = await response.json();
          setItems(data.data.items);
        } catch (error) {
          setError(error.message);
        } finally {
          setLoading(false);
        }
      };
      fetchData();
    } else {
      setLoading(false);
      setItems([]);
    }
  }, []);

  const clearCart = async () => {
    try {
      const response = await fetch(`${config.apiUrl}/v1/cart`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Failed to clear cart");
      }

      // Clear the local cart state
      setItems([]);
    } catch (error) {
      setError(error.message);
    }
  };

  const addToCart = async (productId, quantity = 1) => {
    try {
      const response = await fetch(`${config.apiUrl}/v1/cart/items`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${accessToken}`,
        },
        body: JSON.stringify({ productId, quantity }),
      });
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Failed to add item to cart");
      }
      const data = await response.json();
      setItems(data.data.items);
    } catch (error) {
      setError(error.message);
    }
  };

  const removeFromCart = async (itemId) => {
    try {
      const response = await fetch(`${config.apiUrl}/v1/cart/items/${itemId}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Failed to remove item from cart");
      }
      const data = await response.json();
      setItems(data.data.items);
    } catch (error) {
      setError(error.message);
      // Optionally handle error feedback to the user
    }
  };

  const values = {
    items,
    loading,
    error,
    addToCart,
    removeFromCart,
    clearCart,
    refetchCart: fetchCart,
  };

  return <CartContext.Provider value={values}>{children}</CartContext.Provider>;
};
