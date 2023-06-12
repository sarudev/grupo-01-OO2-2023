import { createBrowserRouter, useRouteError } from 'react-router-dom'
import Index from './pages/Index'
import Edificio from './pages/Edificio'
import Aula from './pages/Aula'
import aulaLoader from './pages/loaders/aula'
import NotFound from './pages/NotFound'
import edificioLoader from './pages/loaders/edificio'
import { type AxiosError } from 'axios'

const router = createBrowserRouter([
  {
    path: '/',
    element: <Index />
  },
  {
    path: '/edificio/:buildingName',
    loader: edificioLoader,
    element: <Edificio />,
    errorElement: <ErrorBoundary />
  },
  {
    path: '/edificio/:buildingName/aula/:aulaName',
    loader: aulaLoader,
    element: <Aula />
  },
  {
    path: '*',
    element: <NotFound />
  }
])
function ErrorBoundary () {
  const error = useRouteError() as AxiosError
  console.error(error)
  return <div>Dang!</div>
}
export default router
