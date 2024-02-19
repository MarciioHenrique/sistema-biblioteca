import React from 'react';
import '../styles/pages/home.css';
import BackgroundLeft from '../components/background-left';

function Home() {
    return (
        <div className="background">
            <BackgroundLeft page="home"/>
            <div className="background-right">
                <h1>Bem Vindo(a)!</h1>
                <h3>Selecione a opção desejada para utilizar nosso sistema.</h3>
            </div>
        </div>
    );
}

export default Home;
