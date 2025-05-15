import { Route, Routes } from "react-router-dom";
import Home from "./components/Home/Home";
import NavbarApp from "./components/Navbar/NavbarApp";
import About from "./components/About/About";
import Login from "./components/Login/Login";
import Register from "./components/Register/Register";
import ProductsList from "./components/Products/ProductsList";
import ProductDetails from "./components/Products/ProductDetails";
import Cart from "./components/Cart/Cart";
import Checkout from "./components/Checkout/Checkout";
import { AuthProvider } from "./contexts/AuthContext";
import { CartProvider } from "./contexts/CartContext";

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <CartProvider>
          <NavbarApp />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/products" element={<ProductsList />} />
            <Route path="/products/:productId" element={<ProductDetails />} />
            <Route path="/about" element={<About />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/cart" element={<Cart />} />
            <Route path="/checkout" element={<Checkout />} />
          </Routes>
        </CartProvider>
      </AuthProvider>
    </div>
  );
}

export default App;
