export function getBuildingDataset (e: Event) {
  const elem = e.currentTarget as SVGGElement
  const dataset = elem.dataset as { buildingName: string, buildingNumber: string }
  console.log(dataset)

  return {
    elem,
    dataset
  }
}
