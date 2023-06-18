import { createBrowserRouter } from 'react-router-dom'
import Index from './pages/Index'
import NotFound from './pages/NotFound'
import Login from './pages/Login'
import Lugar from './pages/Lugar'
import loader from './pages/router/loader'
import ErrorBoundary from './pages/router/ErrorBoundary'
import { useCallback } from 'react'
import { Routes } from './types/enums'
import Logout from './pages/Logout'

const router = createBrowserRouter([
  {
    path: '/',
    element: <Index />
  },
  {
    path: Routes.Edificio,
    loader: loader(({ buildingName }) => `/edificio/${buildingName!}`),
    element: <LugarRouter tipo='edificio' />,
    errorElement: <ErrorBoundary />
  },
  {
    path: Routes.Aula,
    loader: loader(({ buildingName, aulaName }) => `/edificio/${buildingName!}/aula/${aulaName!}`),
    element: <LugarRouter tipo='aula' />
  },
  {
    path: Routes.Login,
    element: <Login />
  },
  {
    path: Routes.Logout,
    element: <Logout />
  },
  {
    path: '*',
    element: <NotFound />
  }
])

function LugarRouter ({ tipo }: { tipo: string }) {
  const LugarComponent = useCallback(() => <Lugar />, [tipo])

  return <LugarComponent />
}

export default router
