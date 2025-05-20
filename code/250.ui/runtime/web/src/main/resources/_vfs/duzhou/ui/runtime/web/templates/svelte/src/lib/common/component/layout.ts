export interface Align {
  row: string;
  col: string;
}

export function sizeToClass(prefix:string, size:string) {
  switch(size) {
    case 'match_parent':
    case 'fill_remains':
    case 'wrap_content': {
      return [prefix + size, null];
    }
    default: {
      return [null, size];
    }
  }
}

export function alignToClass(align:Align) {
  return align && ('align-h-' + (align.row || 'auto') + ' align-v-' + (align.col || 'auto'));
}
