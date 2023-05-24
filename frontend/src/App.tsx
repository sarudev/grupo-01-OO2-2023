import { useState } from 'react'
import './App.scss'
import { ReactComponent as Campus } from './assets/campus.svg'
import useBuildingSelector from './hooks/useBuildingSelector'
import useBuildingNameSelector from './hooks/useBuildingNameSelector'
import { useLoaderData, createBrowserRouter, RouterProvider } from 'react-router-dom'

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

  return (
    <div style={{ width: '100%', height: '100%' }}>
      <MouseCartelito text={currentBuilding} />
      <Campus />
    </div>
  )
}

function MouseCartelito ({ text }: { text: string }) {
  return (
    <div style={{ position: 'absolute', backgroundColor: '#242424', color: 'white', fontSize: '20px', whiteSpace: 'nowrap' }} id='cartelitowo'>
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
    path: '/edificio/:buildingId',
    loader: async ({ params }) => {
      const { buildingId } = params
      return campus.edificios.find(e => e.id === Number(buildingId))
    },
    element: <Building />
  },
  {
    path: '/edificio/:buildingId/aula/:aulaId',
    loader: async ({ params }) => {
      const { buildingId, aulaId } = params
      return campus.edificios.find(e => e.id === Number(buildingId))?.aulas.find(a => a.id === Number(aulaId))
    },
    element: <Aula />
  }
])

export default Router
