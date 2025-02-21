import {useState} from "react"
import Button from "./Bouton.tsx";

const Parent = () => {
    const [isLogged, setIsLogged] = useState(false);

    const updateLogged = () => {
        setIsLogged(!isLogged);
    }

    return (
        <>
            {isLogged ?
                (<Button value={"Logout"} updateLogged={updateLogged} />):
                (<Button value={"Login"} updateLogged={updateLogged} />)
            }
        </>

    )
}
export default Parent