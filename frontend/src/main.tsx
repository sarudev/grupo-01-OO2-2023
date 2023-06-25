import { RouterProvider } from 'react-router-dom'
import router from './Router.tsx'
import { Provider } from 'react-redux'
import store from './redux/store'
import { createRoot } from 'react-dom/client'
import './styles/index.css'

createRoot(document.getElementById('root') as HTMLElement).render(
  <Provider store={store}>
    <RouterProvider router={router} />
  </Provider>
)
