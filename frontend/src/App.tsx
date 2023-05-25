import { useState } from 'react'
import './App.scss'
import { ReactComponent as Campus } from './assets/campus.svg'
import useBuildingSelector from './hooks/useBuildingSelector'
import useBuildingNameSelector from './hooks/useBuildingNameSelector'
import { useLoaderData, createBrowserRouter, RouterProvider } from 'react-router-dom'
import useBuildingNavigation from './hooks/useBuildingNavigation'

const campus = {
  edificios: [
    {
      id: 1,
      nombre: 'José Hernández',
      sensores: [
        {
          id: 1,
          type: 'tiempo'
        }
      ],
      aulas: [
        {
          id: 1,
          nombre: '11',
          sensores: [
            {
              id: 2,
              type: 'tiempo'
            }
          ]
        }
      ]
    }
  ],
  espaciosVerdes: [],
  parkings: []
}

function App () {
  const [currentBuilding, setCurrentBuilding] = useState('')
  useBuildingSelector('cartelitowo', setCurrentBuilding)
  useBuildingNameSelector()

  useBuildingNavigation()

  return (
    <div style={{ width: '100%', height: '100%' }}>
      <MouseCartelito text={currentBuilding} />
      <Campus />
    </div>
  )
}

function MouseCartelito ({ text }: { text: string }) {
  return (
    <div id='cartelitowo'>
      {text}
    </div>
  )
}

function Building () {
  const building = useLoaderData() as typeof campus.edificios

  return (
    <div>
      {JSON.stringify(building, null, 2)}
    </div>
  )
}

function Aula () {
  const aula = useLoaderData() as { id: number, nombre: string, sensores: Array<{ id: number, type: string }> }

  return (
    <div>
      {JSON.stringify(aula, null, 2)}
    </div>
  )
}

function Router () {
  return (
    <RouterProvider router={router} />
  )
}

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />
  },
  {
    path: '/edificio/:buildingName',
    loader: async ({ params }) => {
      const { buildingName } = params
      return campus.edificios.find(e => e.nombre === buildingName!.replaceAll('-', ' ')) ?? { error: '404 | Not Found' }
    },
    element: <Building />
  },
  {
    path: '/edificio/:buildingId/aula/:aulaName',
    loader: async ({ params }) => {
      const { buildingName, aulaName } = params
      return campus.edificios.find(e => e.nombre === buildingName!.replaceAll('-', ' '))?.aulas.find(a => a.nombre === aulaName!.replaceAll('-', '')) ?? { error: '404 | Not Found' }
    },
    element: <Aula />
  }
])

export default Router
