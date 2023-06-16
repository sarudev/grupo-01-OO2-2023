import { useState, useCallback } from 'react'

export default function useSelect <T extends string> (options: T[]) {
  const [option, setOption] = useState<T>(options[0] as T)

  const Select = useCallback(() => (
    <select onChange={(e) => setOption(e.target.value as T)} className="filter-input filter-sensor">
      {options.map(op => <option key={op} value={op}>{op}</option>)}
    </select>
  ), [])

  return {
    Select,
    option
  }
}
