import { type AxiosError } from 'axios'
import { useRouteError } from 'react-router-dom'

export default function ErrorBoundary () {
  const error = useRouteError() as AxiosError
  console.error(error)
  return <div>Dang!</div>
}
