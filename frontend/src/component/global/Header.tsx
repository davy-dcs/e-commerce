import Logo from "../logo/Logo.tsx";
import Search from "../search/Search.tsx";
import Cart from "../cart/Cart.tsx";

const Header = () => {
    return (
        <header className="bg-amber-400 px-3 py-2 flex items-center justify-between w-full">
            <Logo />
            <div className={"flex items-center justify-center space-x-2"}>
                <Search />
                <Cart />
            </div>

        </header>
    )
}
export default Header