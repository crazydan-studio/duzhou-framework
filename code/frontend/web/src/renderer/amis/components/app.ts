/*
 * 渡舟平台 - 致力于构建自运维、自监控、可演化的全功能型应用平台
 * Copyright (C) 2024 Crazydan Studio <https://studio.crazydan.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.
 * If not, see <https://www.gnu.org/licenses/lgpl-3.0.en.html#license-text>.
 */

import { AppStore, RegisterStore } from 'amis';

// Note：备用代码，用于演示如何创建并注册新的 Store
export const NewAppStore = AppStore.named('NewAppStore').actions((self) => ({
  // afterCreate() {
  //   // console.log('create', this.setActivePage, self.setActivePage);
  //   const old_setActivePage = self.setActivePage;
  //   self.setActivePage = (...args) => {
  //     console.log('set', ...args);
  //     old_setActivePage.call(...args);
  //   };
  // },
  // setActivePage() {
  //   console.log('set', this, self);
  // },
  // updateActivePage() {
  //   console.log('update', this.setActivePage, self.setActivePage);
  // }
}));

RegisterStore(NewAppStore);
