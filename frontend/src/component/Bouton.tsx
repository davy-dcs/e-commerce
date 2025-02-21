type BoutonProps = {
    value: string,
    updateLogged: () => void,
}
const Bouton: React.FC<BoutonProps> = (props) => {
    return (<button onClick={props.updateLogged}>{props.value}</button>)
}
export default Bouton