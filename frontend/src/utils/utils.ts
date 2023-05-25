export function getSvgCampusDataset (e: Event) {
  const elem = e.target as SVGGElement
  const dataset = elem.dataset as { buildingName: string, buildingNumber: string }

  return {
    elem,
    dataset
  }
}
