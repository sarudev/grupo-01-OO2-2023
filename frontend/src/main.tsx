import { RouterProvider } from 'react-router-dom'
import router from './Router.tsx'
import { Provider } from 'react-redux'
import store from './redux/store'
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './styles/index.css'

createRoot(document.getElementById('root') as HTMLElement).render(
  <StrictMode>
    <Provider store={store}>
      <RouterProvider router={router} />
    </Provider>
  </StrictMode>
)
