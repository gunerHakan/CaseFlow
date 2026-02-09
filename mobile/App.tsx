import React, { useState } from 'react';
import { StatusBar, StyleSheet, Text, View } from 'react-native';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import LoginScreen from './screens/LoginScreen';

function App() {
  const [token, setToken] = useState<string | null>(null);

  const handleLoginSuccess = (jwt: string) => {
    setToken(jwt);
  };

  return (
    <SafeAreaProvider>
      <StatusBar barStyle="dark-content" />
      <View style={styles.container}>
        {token ? (
          <View style={styles.mainContainer}>
            <Text style={styles.welcomeTitle}>CaseFlow</Text>
            <Text style={styles.welcomeSubtitle}>
              Başarıyla giriş yaptınız. Bu alan ileride ana ekran ile
              değiştirilecek.
            </Text>
          </View>
        ) : (
          <LoginScreen onLoginSuccess={handleLoginSuccess} />
        )}
      </View>
    </SafeAreaProvider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#FFFFFF',
  },
  mainContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 24,
  },
  welcomeTitle: {
    fontSize: 26,
    fontWeight: 'bold',
    color: '#0D47A1',
    marginBottom: 12,
  },
  welcomeSubtitle: {
    fontSize: 16,
    color: '#555',
    textAlign: 'center',
  },
});

export default App;
