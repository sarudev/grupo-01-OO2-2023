import { useMemo } from 'react'
import { type ILugarTipo, LugarDependencia } from '../types/enums'

export default function useLugarDependencia (lugarTipo: ILugarTipo) {
  const nombreDependencia = useMemo(() => LugarDependencia[lugarTipo], [])

  return { withoutS: nombreDependencia, withS: nombreDependencia?.slice(0, -1) }
}
