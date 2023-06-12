import { type LoaderFunctionArgs } from 'react-router-dom'
import axios from 'axios'

export default async function edificioLoader ({ params }: LoaderFunctionArgs) {
  const { buildingName } = params as { buildingName: string }

  const { data } = await axios.get(`http://186.129.26.42:5282/edificio/${buildingName.replaceAll(' ', '-')}`)

  return data
}
