import Home from "./component/layout/Home.tsx";
import Cart from "./component/layout/Cart.tsx";
import {Route, Routes} from "react-router-dom";
import BasicTemplate from "./component/template/BasicTemplate.tsx";

function App() {
    return (
        <Routes>
            <Route path="/" element={<BasicTemplate children={<Home/>} />} />
            <Route path="/cart" element={<BasicTemplate children={<Cart/>} />} />
        </Routes>
    )
}

export default App
