import React from "react";

const Receipt: React.FC = () => {
    return (
        <aside className={"w-64"}>
            <div className={"bg-gray-200 rounded shadow-inner border border-gray-300"}>
                <h3 className={"p-4"}>Résumé</h3>
                <hr className={"text-gray-400"}/>
                <h4>Liste d'articles</h4>
                <hr className={"text-gray-400"}/>
                <h4>Total</h4>
            </div>
            <button>Valider</button>
        </aside>
    )
}
export default Receipt