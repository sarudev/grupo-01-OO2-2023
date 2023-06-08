import { memo } from 'react'
import useDynamicSVGImport from '../hooks/useDynamicSVGImport'

export default memo(function Icon ({ svgName }: { svgName: string }) {
  const { SVG } = useDynamicSVGImport(svgName)

  if (SVG == null) return null

  return <SVG />
})
