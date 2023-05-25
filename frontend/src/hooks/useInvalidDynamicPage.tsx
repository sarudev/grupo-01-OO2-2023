import { useLayoutEffect, useState } from 'react'
import { type Status } from '../types/types'

export default function useInvalidDynamicPage (status: Status) {
  const [notFound, setNotFound] = useState(false)

  useLayoutEffect(() => {
    if (status.code === 404) {
      setNotFound(true)
    }
  }, [])

  return notFound
}
