import React, {useState} from "react";
import ProductList from "../product/ProductList.tsx";
import Nav from "../global/Nav.tsx";

const Home: React.FC = () => {
    const [type, setType] = useState("FRUITS")
    return (
        <main className={"flex space-x-5 w-7xl mx-auto my-10"}>
            <Nav type={type} setType={setType} />
            <ProductList type={type} />
        </main>
    )
}

export default Home