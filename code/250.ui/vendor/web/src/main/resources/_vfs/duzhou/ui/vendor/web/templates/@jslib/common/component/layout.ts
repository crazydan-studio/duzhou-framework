import { sizeToNative } from '../utils.ts';

/** 布局对齐 */
export interface Align {
  row: string;
  col: string;
}

/**
 * 将布局尺寸转换为 class 样式名
 * @return [class 名, 实际尺寸]
 */
export function layoutSizeToClass(prefix:string, size:string) {
  switch(size) {
    case 'match_parent':
    case 'fill_remains':
    case 'wrap_content': {
      return [prefix + size, null];
    }
    default: {
      return [null, sizeToNative(size)];
    }
  }
}

/** 将布局对齐模式转换为 class 样式名 */
export function layoutAlignToClass(align:Align) {
  return align && ('align-h-' + (align.row || 'auto') + ' align-v-' + (align.col || 'auto'));
}
