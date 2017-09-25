import { ConfagridPage } from './app.po';

describe('confagrid App', () => {
  let page: ConfagridPage;

  beforeEach(() => {
    page = new ConfagridPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
