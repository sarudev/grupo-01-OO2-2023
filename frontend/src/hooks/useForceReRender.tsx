import { useState } from 'react'

export default function useForceReRender () {
  const [, setValue] = useState(0)
  return () => setValue(value => value + 1)
}
