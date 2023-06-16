import { type LoaderFunctionArgs } from 'react-router-dom'
import axios from 'axios'
const { VITE_API_URL } = import.meta.env as Record<string, string>

export default async function edificioLoader ({ params }: LoaderFunctionArgs) {
  const { buildingName } = params as { buildingName: string }

  if (VITE_API_URL == null) {
    console.error('VITE_API_URL is required in .env')
    return null
  }

  const { data } = await axios.get(`http://${VITE_API_URL}:5282/edificio/${buildingName.replaceAll(' ', '-')}`)

  return data
}
