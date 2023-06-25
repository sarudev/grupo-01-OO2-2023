import { useState, useCallback, useEffect, useRef } from 'react'

export default function useInput (placeholder: string) {
  const [input, setInput] = useState('')
  const inputRef = useRef<HTMLInputElement>(null)

  useEffect(() => {
    if (inputRef.current == null) return

    inputRef.current.focus()
  })

  const Input = useCallback(() => (
    <input
      ref={inputRef}
      value={input}
      className='filter-input filter-dependencia'
      type="text"
      placeholder={placeholder}
      onChange={(e) => setInput(e.target.value)}
    />
  ), [input])

  return {
    Input,
    input
  }
}
