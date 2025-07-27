describe('Failing Test', () => {
  it('should intentionally fail', () => {
    // Ce test échouera toujours
    expect(true).withContext('Ce test échoue intentionnellement pour tester le workflow GitHub Actions').toBe(false);
  });
});
