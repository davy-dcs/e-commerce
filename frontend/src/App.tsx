import Header from './component/partial/Header.tsx'
import Product from "./component/Product.tsx";
import {useState} from "react";
import Parent from "./component/Parent.tsx";
import ProductList from "./component/ProductList.tsx";

function App() {
  const [product] = useState({
    name: "Nike Air Max 270 React",
    price: 160,
    description: "Description de la nike",
    stock: 10,
  });

  return (
      <>
        <Header/>
        <Product
          name={product.name}
          price={product.price}
          description={product.description}
          stock={product.stock}
        />
        <Parent/>
        <ProductList/>
      </>
  )
}

export default App
