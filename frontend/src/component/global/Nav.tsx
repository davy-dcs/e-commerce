import React, {useEffect, useState} from "react";
import axios from "axios";

type NavProps = {
    setType: (type: string) => void,
    type: string
}

const Nav: React.FC<NavProps> = (props) => {
    const [productTypes, setProductTypes] = useState<string[] | null>(null)

    const auth = {
        username: "Davy",
        password: "password",
    }
    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/products/types", { auth })
            .then((response) => {
                setProductTypes(response.data);
            })
            .catch((error) => {console.error("Erreur lors de la récupération des types de produit :", error)});
    }, [props.type]);
    return (
        <nav>
            <ul>
                {productTypes && productTypes.map((type) => (
                    <li className={(props.type === type) ? "text-sm whitespace-nowrap font-bold pointer-events-none":"text-sm whitespace-nowrap cursor-pointer"} key={type} onClick={() => props.setType(type)}>

                            {type.toLowerCase().replace(/_/g, " ").replace(/\b\w/g, (match) => match.toUpperCase())}

                    </li>
                ))}
            </ul>
        </nav>
    )
}
export default Nav