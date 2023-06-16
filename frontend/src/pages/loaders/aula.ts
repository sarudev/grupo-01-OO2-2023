import { type LoaderFunctionArgs } from 'react-router-dom'
import axios from 'axios'
const { VITE_API_URL } = import.meta.env as Record<string, string>

export default async function aulaLoader ({ params }: LoaderFunctionArgs) {
  const { buildingName, aulaName } = params as { buildingName: string, aulaName: string }

  const { data } = await axios.get(`http://${VITE_API_URL}:5282/edificio/${buildingName}/aula/${aulaName}`)

  return data
}
