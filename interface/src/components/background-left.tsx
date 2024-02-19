import React from "react";
import { useNavigate } from "react-router-dom";
import "../styles/components/background-left.css";

function BackgroundLeft() {
    const navigate = useNavigate();
    const handleClick = () => {
        navigate("/alunos");
    }
    return (
        <div className="background-left">
            <div className="background-left-title">
                <label>Biblioteca</label>
            </div>
            <div className="background-left-options">
                <div className="background-left-option" onClick={handleClick}>
                    <label>Alunos</label>
                </div>
                <div className="background-left-option">
                    <label>Livros</label>
                </div>
                <div className="background-left-option">
                    <label>Emprestimo</label>
                </div>
                <div className="background-left-option">
                    <label>Devolução</label>
                </div>
                

            </div>
            <div className="background-left-footer">
                <label>Desenvolvido por: <strong>Marcio Henrique</strong></label>
            </div>

        </div>
    );
}

export default BackgroundLeft;