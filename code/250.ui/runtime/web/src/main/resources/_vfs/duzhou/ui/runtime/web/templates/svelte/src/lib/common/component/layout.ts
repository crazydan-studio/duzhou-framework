export interface Align {
  h: string;
  v: string;
}

export function sizeToClass(prefix:string, size:string) {
  return size && (prefix + size);
}

export function alignToClass(align:Align) {
  return align && ('align-h-' + (align.h || 'auto') + ' align-v-' + (align.v || 'auto'));
}
