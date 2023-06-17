import { createBrowserRouter } from 'react-router-dom'
import Index from './pages/Index'
import NotFound from './pages/NotFound'
import Login from './pages/Login'
import Lugar from './pages/Lugar'
import loader from './pages/router/loader'
import ErrorBoundary from './pages/router/ErrorBoundary'

const router = createBrowserRouter([
  {
    path: '/',
    element: <Index />
  },
  {
    path: '/edificio/:buildingName',
    loader: loader(({ buildingName }) => `/edificio/${buildingName!}`),
    element: <Lugar />,
    errorElement: <ErrorBoundary />
  },
  {
    path: '/edificio/:buildingName/aula/:aulaName',
    loader: loader(({ buildingName, aulaName }) => `/edificio/${buildingName!}/aula/${aulaName!}`),
    element: <Lugar />
  },
  {
    path: '/login',
    element: <Login />
  },
  {
    path: '*',
    element: <NotFound />
  }
])

export default router
