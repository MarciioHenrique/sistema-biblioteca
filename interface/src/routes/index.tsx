import React from 'react';
import { Fragment } from "react";
import { BrowserRouter, Route, Routes as RouterRoutes } from "react-router-dom";
import Home from '../pages/home';
import Alunos from '../pages/alunos';

const Routes = () => {
  return (
    <BrowserRouter>
      <Fragment>
        <RouterRoutes>
            <Route path="*" element={<Home />} />
            <Route path="/" element={<Home />} />
            <Route path="/alunos" element={<Alunos />} />
        </RouterRoutes>
      </Fragment>
    </BrowserRouter>
  );
};

export default Routes;