import { type LoaderFunctionArgs } from 'react-router-dom'
import axios from 'axios'
export default async function aulaLoader ({ params }: LoaderFunctionArgs) {
  const { buildingName, aulaName } = params as { buildingName: string, aulaName: string }

  const { data } = await axios.get(`http://localhost:5282/edificio/${buildingName}/aula/${aulaName}`)

  return data
}
