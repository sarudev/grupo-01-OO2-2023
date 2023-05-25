import { createBrowserRouter } from 'react-router-dom'
import Index from './pages/Index'
import Edificio, { loader as edificioLoader } from './pages/Edificio'
import Aula, { loader as aulaLoader } from './pages/Aula'
import NotFound from './pages/NotFound'
import './styles/App.scss'

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
