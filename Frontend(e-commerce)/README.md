# E-Commerce Frontend

A modern e-commerce frontend built with React. This project provides a complete shopping experience with features like product browsing, cart management, and checkout process.

## Features

- 🛍️ Product browsing with categories
- 🔍 Product search and filtering
- 🛒 Shopping cart functionality
- 👤 User authentication (Login/Register)
- 💳 Checkout process
- 📱 Responsive design
- ⭐ Product ratings and reviews
- 🎨 Modern UI with Bootstrap

## Tech Stack

- React.js
- React Router for navigation
- Bootstrap for styling
- FontAwesome for icons
- SweetAlert2 for notifications
- Fake Store API for product data

## Getting Started

### Prerequisites

- Node.js (v14 or higher)
- npm or yarn

### Installation

1. Clone the repository
```bash
git clone [your-repo-url]
cd ecommerce-app
```

2. Install dependencies
```bash
npm install
# or
yarn install
```

3. Start the development server
```bash
npm run dev
# or
yarn dev
```

The application will be available at `http://localhost:5173`

## Project Structure

```
src/
├── components/         # React components
│   ├── About/         # About page components
│   ├── Cart/          # Cart related components
│   ├── Checkout/      # Checkout process components
│   ├── Contact/       # Contact page components
│   ├── Home/          # Home page components
│   ├── Login/         # Authentication components
│   ├── Navbar/        # Navigation components
│   ├── Products/      # Product listing and details
│   ├── Register/      # Registration components
│   └── UsersMsg/      # User notification components
├── images/            # Static images
├── store/             # Redux store configuration
│   └── slices/        # Redux slices
└── App.jsx           # Main application component
```

## API Integration

The frontend currently uses the Fake Store API for product data. To integrate with your backend:

1. Update the API endpoints in the following files:
   - `src/components/Products/ProductDetails.jsx`
   - `src/store/slices/products-slice.js`

2. Configure your backend API URL in the environment variables

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details

## Contact

Your Name - [your-email@example.com]

Project Link: [https://github.com/yourusername/ecommerce-app]
