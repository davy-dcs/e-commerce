import React from "react";
import Header from "../global/Header.tsx";

type BasicTemplateProps = {
    children: React.ReactNode;
}

const BasicTemplate: React.FC<BasicTemplateProps> = (props) => {
    return (
        <React.Fragment>
            <Header />
            {props.children}
        </React.Fragment>
    )
}
export default BasicTemplate