import { type LoaderFunction, type Params } from 'react-router-dom'
import { type LoaderResponse } from '../../types/types'
import axios, { AxiosError } from 'axios'
const { VITE_API_URL } = import.meta.env as Record<string, string>

export default function loader (path: (params: Params<string>) => string): LoaderFunction {
  return async ({ params }) => {
    let response: LoaderResponse

    try {
      axios.defaults.withCredentials = true
      const res = await axios.get(`http://${VITE_API_URL}:5282${path(params)}`)
      // const data = await fetch(`http://${VITE_API_URL}:5282${path(params)}`, {
      //   method: 'get',
      //   mode: 'cors',
      //   credentials: 'include',
      //   headers: {
      //     accept: 'application/json'
      //   }
      // })
      // const res = await data.json()
      response = { lugar: res.data, status: res.status, userRole: null, serverWorking: true }
    } catch (err: unknown) {
      console.log(err)
      if (err instanceof AxiosError) {
        const status = err.response?.status ?? 500
        response = { lugar: null, status, userRole: null, serverWorking: status !== 500 }
      }
      response = { lugar: null, status: 500, userRole: null, serverWorking: false }
    }

    return response
  }
}
