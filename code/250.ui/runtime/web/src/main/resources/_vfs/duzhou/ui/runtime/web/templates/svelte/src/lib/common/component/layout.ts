export interface Align {
  row: string;
  col: string;
}

export function sizeToClass(prefix:string, size:string) {
  return size && (prefix + size);
}

export function alignToClass(align:Align) {
  return align && ('align-h-' + (align.row || 'auto') + ' align-v-' + (align.col || 'auto'));
}
