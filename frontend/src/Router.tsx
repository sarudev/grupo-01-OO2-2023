import { createBrowserRouter } from 'react-router-dom'
import Index from './pages/Index'
import Edificio from './pages/Edificio'
import Aula from './pages/Aula'
import aulaLoader from './pages/loaders/aula'
import NotFound from './pages/NotFound'
import edificioLoader from './pages/loaders/edificio'

const router = createBrowserRouter([
  {
    path: '/',
    element: <Index />
  },
  {
    path: '/edificio/:buildingName',
    loader: edificioLoader,
    element: <Edificio />
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

export default router
