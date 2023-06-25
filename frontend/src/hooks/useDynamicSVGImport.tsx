import type React from 'react'
import { useEffect, useRef, useState } from 'react'

export default function useDynamicSVGImport (svgName: string, onComplete?: () => void) {
  const [loading, setLoading] = useState(false)
  const ImportedIconRef = useRef<React.FC<React.SVGProps<SVGSVGElement>>>()

  useEffect(() => {
    setLoading(true)
    const importIcon = async () => {
      try {
        ImportedIconRef.current = (
          await import(`../assets/${svgName}.svg`)
        ).ReactComponent
        if (onComplete != null) onComplete()
      } catch (err: unknown) {
        console.error(err)
      } finally {
        setLoading(false)
      }
    }
    void importIcon()
  }, [])

  return { SVG: ImportedIconRef.current, loading }
}
