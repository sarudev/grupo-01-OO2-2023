import { createBrowserRouter } from 'react-router-dom'
import Index from './pages/Index'
import Status from './pages/Status'
import Login from './pages/Login'
import Lugar from './pages/Lugar'
import loader from './pages/router/loader'
import ErrorBoundary from './pages/router/ErrorBoundary'
import { useCallback } from 'react'
import { Routes } from './types/enums'
import Logout from './pages/Logout'
import LoadingDB from './pages/LoadingDB'
import IndexLoader from './pages/router/IndexLoader'

const router = createBrowserRouter([
  {
    path: '/',
    loader: IndexLoader(),
    element: <Index />
  },
  {
    path: Routes.Building,
    loader: loader(({ buildingType, buildingName }) => `/${buildingType!}/${buildingName!}`),
    element: <LugarRouter tipo='Building' />,
    errorElement: <ErrorBoundary />
  },
  {
    path: Routes.Dependency,
    loader: loader(({ buildingType, buildingName, dependencyType, dependencyName }) => `/${buildingType!}/${buildingName!}/${dependencyType!}/${dependencyName!}`),
    element: <LugarRouter tipo='Dependency' />
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
    path: Routes.LoadDB,
    element: <LoadingDB />
  },
  {
    path: '*',
    element: <Status code={404} goto='/' gotoMessage='Volver al campus' statusMessage='Página no encontrada' />
  }
])

function LugarRouter ({ tipo }: { tipo: string }) {
  const LugarComponent = useCallback(() => <Lugar />, [tipo])

  return <LugarComponent />
}

export default router
