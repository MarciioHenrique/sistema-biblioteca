import React from 'react';
import { Fragment } from "react";
import { BrowserRouter, Route, Routes as RouterRoutes } from "react-router-dom";
import Home from '../pages/home';
import Alunos from '../pages/alunos';
import Livros from '../pages/livros';
import Emprestimo from '../pages/emprestimo';
import Devolucao from '../pages/devolucao';

const Routes = () => {
  return (
    <BrowserRouter>
      <Fragment>
        <RouterRoutes>
            <Route path="*" element={<Home />} />
            <Route path="/" element={<Home />} />
            <Route path="/alunos" element={<Alunos />} />
            <Route path="/livros" element={<Livros />} />            
            <Route path="/emprestimo" element={<Emprestimo />} />
            <Route path="/devolucao" element={<Devolucao />} />
        </RouterRoutes>
      </Fragment>
    </BrowserRouter>
  );
};

export default Routes;