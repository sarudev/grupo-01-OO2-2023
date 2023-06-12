import { type LoaderFunctionArgs } from 'react-router-dom'
import axios from 'axios'
export default async function aulaLoader ({ params }: LoaderFunctionArgs) {
  const { buildingName, aulaName } = params as { buildingName: string, aulaName: string }

  const { data } = await axios.get(`http://186.129.26.42:5282/edificio/${buildingName}/aula/${aulaName}`)

  return data
}
